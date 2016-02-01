/*
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package net.sf.jclal.util.random;

import java.util.Arrays;
import org.apache.commons.lang.builder.EqualsBuilder;

/**
 * <p>RandMar is a lagged Fibonacci generator proposed by Marsaglia and Zaman and
 * is a good research grade generator. This version is based on the paper by
 * James, which is a good reference for the properties of this and several other
 * generators.</p>
 *
 * <p><B>REFERENCES: </B></p>
 *
 * <p>F. James, <cite>Comp. Phys. Comm.</cite> <STRONG>60</STRONG> (1990) p329-344
 * </p>
 *
 * <p>and was originally described in</p>
 *
 * <p>G. Marsaglia, A. Zaman and W.-W Tsang, <cite>Stat. Prob. Lett</cite>
 * <strong>9</strong> (1990) p35. </p>
 *
 */
public class Ranmar extends AbstractRandGen {
    /////////////////////////////////////////////////////////////////
    // --------------------------------------- Serialization constant
    /////////////////////////////////////////////////////////////////

    /**
     * Generated by Eclipse
     */
    private static final long serialVersionUID = 94768486326620566L;
    /////////////////////////////////////////////////////////////////
    // --------------------------------------- Par�metros por defecto
    /////////////////////////////////////////////////////////////////

    private static int BIG_PRIME = 899999963;

    private static final double CD = 7654321.0 / 16777216.0;

    private static final double CM = 16777213.0 / 16777216.0;
    ///////////////////////////////////////////////////////////////////
    // ----------------------------------------------------- Attributes
    ///////////////////////////////////////////////////////////////////
    private int i97;
    private int j97;
    private double c;
    private double[] u;

    /////////////////////////////////////////////////////////////////
    // ------------------------------------------------ Constructores
    /////////////////////////////////////////////////////////////////
    /**
     * Empty constructor.
     */
    protected Ranmar() {
        super();
    }

    /**
     * Default constructor (used by RandGenFactory).
     *
     * @param seed Value used to set-up this random generator
     */
    public Ranmar(int seed) {
        // Call super constructor
        super();
        // Initialize
        ranmarin(seed);
    }

    ////////////////////////////////////////////////////////////////////
    // ----------------------------------- Implementing IRandGen methods
    ////////////////////////////////////////////////////////////////////
    /**
     * {@inheritDoc}
     *
     * @return The number generated.
     */
    @Override
    public final double raw() {
        double uni = u[i97] - u[j97];
        if (uni < 0.0) {
            uni += 1.0;
        }
        u[i97] = uni;
        if (--i97 < 0) {
            i97 = 96;
        }
        if (--j97 < 0) {
            j97 = 96;
        }
        c -= CD;
        if (c < 0.0) {
            c += CM;
        }
        uni -= c;
        if (uni < 0.0) {
            uni += 1.0;
        }
        return (uni);
    }

    /**
     * Overrided to increase efficiency.
     *
     * {@inheritDoc}
     *
     * @param d a vector of doubles
     * @param n n value
     */
    @Override
    public final void raw(double d[], int n) {
        double uni;

        for (int i = 0; i < n; i++) {
            uni = u[i97] - u[j97];
            if (uni < 0.0) {
                uni += 1.0;
            }
            u[i97] = uni;
            if (--i97 < 0) {
                i97 = 96;
            }
            if (--j97 < 0) {
                j97 = 96;
            }
            c -= CD;
            if (c < 0.0) {
                c += CM;
            }
            uni -= c;
            if (uni < 0.0) {
                uni += 1.0;
            }
            d[i] = uni;
        }
    }

    ////////////////////////////////////////////////////////////////////
    // ------------------------------ Overwrite java.lang.Object methods
    ////////////////////////////////////////////////////////////////////
    @Override
    public boolean equals(Object other) {
        if (other instanceof Ranmar) {
            Ranmar o = (Ranmar) other;
            EqualsBuilder eb = new EqualsBuilder();
            eb.append(this.i97, o.i97);
            eb.append(this.j97, o.j97);
            eb.append(this.c, o.c);
            eb.append(this.u, o.u);
            return eb.isEquals();
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + this.i97;
        hash = 23 * hash + this.j97;
        hash = 23 * hash + (int) (Double.doubleToLongBits(this.c) ^ (Double.doubleToLongBits(this.c) >>> 32));
        hash = 23 * hash + Arrays.hashCode(this.u);
        return hash;
    }

    ////////////////////////////////////////////////////////////////////
    // ------------------------------------------------ M�todos privados
    ////////////////////////////////////////////////////////////////////
    /**
     * Setup method.
     *
     * @param ijkl Used to set all seeds
     */
    private final void ranmarin(int ijkl) {
        // Init i97
        i97 = 96;
        // Init j97
        j97 = 32;
        // Init c
        c = 362436.0 / 16777216.0;
        // Init u
        u = new double[97];
        int ij = (ijkl % BIG_PRIME) / 30082;
        int kl = (ijkl % BIG_PRIME) - 30082 * ij;
        int i = ((ij / 177) % 177) + 2;
        int j = (ij % 177) + 2;
        int k = ((kl / 169) % 178) + 1;
        int l = kl % 169;
        for (int ii = 0; ii < 97; ii++) {
            double s = 0.0;
            double t = 0.5;
            for (int jj = 0; jj < 24; jj++) {
                int m = (((i * j) % 179) * k) % 179;
                i = j;
                j = k;
                k = m;
                l = (53 * l + 1) % 169;
                if (((l * m) % 64) >= 32) {
                    s += t;
                }
                t *= 0.5;
            }
            u[ii] = s;
        }
    }
}
