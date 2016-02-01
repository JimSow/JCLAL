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

import net.sf.jclal.core.IRandGen;

/**
 * Mersenne Twister Factory
 *
 * @author Sebastian Ventura
 */
public class RanmtFactory extends AbstractRandGenFactory {
    /////////////////////////////////////////////////////////////////
    // --------------------------------------- Serialization constant
    /////////////////////////////////////////////////////////////////

    /**
     * Generated by Eclipse
     */
    private static final long serialVersionUID = -1837496618859662406L;

    /////////////////////////////////////////////////////////////////
    // ------------------------------------------------- Constructors
    /////////////////////////////////////////////////////////////////
    /**
     * Empty constructor.
     */
    public RanmtFactory() {
        super();
    }

    /////////////////////////////////////////////////////////////////
    // ------------------------- Implementing IRandGenFactory methods 
    /////////////////////////////////////////////////////////////////
    /**
     * {@inheritDoc}
     *
     * @return The random generator.
     */
    @Override
    public IRandGen createRandGen() {
        return new Ranmt(seedGenerator.nextSeed());
    }
}
